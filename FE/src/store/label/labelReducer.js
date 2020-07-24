import {
  GET_LABELS_SUCCESS,
  GET_LABELS_FAILURE,
  CREATE_LABEL_SUCCESS,
  CREATE_LABEL_FAILURE,
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
    case CREATE_LABEL_SUCCESS:
      const updatedLabelList = state.labels
        .concat(payload.labels || payload)
        .sort((a, b) => a.title.localeCompare(b.title));
      return {
        ...state,
        labels: updatedLabelList,
      };
    case DELETE_LABEL_SUCCESS:
      return {
        ...state,
        labels: state.labels.filter((label) => label.id !== payload),
      };
    case GET_LABELS_FAILURE:
    case CREATE_LABEL_FAILURE:
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
