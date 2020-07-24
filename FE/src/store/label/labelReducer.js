import { GET_LABELS_SUCCESS, GET_LABELS_FAILURE } from "./labelAction";

const initialState = {
  error: "",
  labels: [],
};

const labelReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case GET_LABELS_SUCCESS:
      const updatedLabelList = state.labels
        .concat(payload.labels)
        .sort((a, b) => a.title.localeCompare(b.title));
      return {
        ...state,
        labels: updatedLabelList,
      };
    case GET_LABELS_FAILURE:
      return {
        ...state,
        error: payload,
      };
    default:
      return state;
  }
};

export default labelReducer;
