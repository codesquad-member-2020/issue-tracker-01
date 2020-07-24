import { GET_LABELS, GET_LABELS_SUCCESS, GET_LABELS_FAILURE } from "./labelAction";

const initialState = {
  loading: {
    GET_LABELS: false,
    CREATE_LABEL: false,
    EDIT_LABEL: false,
    DELETE_LABEL: false,
  },
  error: "",
  labels: [],
};

const labelReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case GET_LABELS:
      return {
        ...state,
        loading: {
          ...state.loading,
          GET_LABELS: true,
        },
      };
    case GET_LABELS_SUCCESS:
      const updatedLabelList = state.labels
        .concat(payload.labels)
        .sort((a, b) => a.title.localeCompare(b.title));
      return {
        ...state,
        loading: {
          ...state.loading,
          GET_LABELS: false,
        },
        labels: updatedLabelList,
      };
    case GET_LABELS_FAILURE:
      return {
        ...state,
        loading: {
          ...state.loading,
          GET_LABELS: false,
        },
        error: payload,
      };
    default:
      return state;
  }
};

export default labelReducer;
