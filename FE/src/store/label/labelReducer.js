import {
  GET_LABELS_SUCCESS,
  GET_LABELS_FAILURE,
  CREATE_LABEL_SUCCESS,
  CREATE_LABEL_FAILURE,
  EDIT_LABEL_SUCCESS,
  EDIT_LABEL_FAILURE,
  DELETE_LABEL_SUCCESS,
  DELETE_LABEL_FAILURE,
} from "./labelAction";

const initialState = {
  error: "",
  labels: [],
};

const labelReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case GET_LABELS_SUCCESS:
      return {
        ...state,
        labels: payload.data.labels.sort((a, b) => a.title.localeCompare(b.title)),
      };
    case CREATE_LABEL_SUCCESS:
      const updatedLabelList = state.labels
        .concat(payload)
        .sort((a, b) => a.title.localeCompare(b.title));
      return {
        ...state,
        labels: updatedLabelList,
      };
    case EDIT_LABEL_SUCCESS:
      const {
        labelId,
        updatedValues: { title, description, color },
      } = payload;
      const updatedList = state.labels
        .map((label) => (label.id === labelId ? { ...label, title, description, color } : label))
        .sort((a, b) => a.title.localeCompare(b.title));
      return {
        ...state,
        labels: updatedList,
      };
    case DELETE_LABEL_SUCCESS:
      return {
        ...state,
        labels: state.labels.filter((label) => label.id !== payload),
      };
    case GET_LABELS_FAILURE:
    case CREATE_LABEL_FAILURE:
    case EDIT_LABEL_FAILURE:
    case DELETE_LABEL_FAILURE:
      return {
        ...state,
        error: payload,
      };
    default:
      return state;
  }
};

export default labelReducer;
