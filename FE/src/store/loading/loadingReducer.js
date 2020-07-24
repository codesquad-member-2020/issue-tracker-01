import { START_LOADING, FINISH_LOADING } from "./loadingAction";

const initialState = {};

const loadingReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case START_LOADING:
      return {
        ...state,
        [payload]: true,
      };
    case FINISH_LOADING:
      return {
        ...state,
        [payload]: false,
      };
    default:
      return state;
  }
};

export default loadingReducer;
