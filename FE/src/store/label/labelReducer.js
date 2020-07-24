import {
  GET_LABELS_SUCCESS,
  GET_LABELS_FAILURE,
  CREATE_LABEL_SUCCESS,
  CREATE_LABEL_FAILURE,
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
    case GET_LABELS_FAILURE:
    case CREATE_LABEL_FAILURE:
      return {
        ...state,
        error: payload,
      };
    default:
      return state;
  }
};

export default labelReducer;
