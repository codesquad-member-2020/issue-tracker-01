import { FETCH_LABELS_REQUEST, FETCH_LABELS_SUCCESS, FETCH_LABELS_FAILURE } from "./labelAction";

const initialState = {
  loading: false,
  error: "",
  labels: [],
};

const labelReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case FETCH_LABELS_REQUEST:
      return {
        ...state,
        loading: true,
      };
    case FETCH_LABELS_SUCCESS:
      return {
        ...state,
        loading: false,
      };
    case FETCH_LABELS_FAILURE:
      return {
        ...state,
        loading: false,
        error: payload,
      };
    default:
      return state;
  }
};

export default labelReducer;
