import {
  GET_ISSUES_SUCCESS,
  GET_ISSUES_FAILURE,
  CREATE_ISSUE_SUCCESS,
  CREATE_ISSUE_FAILURE,
} from "./issueAction";

const initialState = {
  error: "",
  issues: [],
};

const issueReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case GET_ISSUES_SUCCESS:
      return {
        ...state,
        issues: payload.data.issues,
      };
    case GET_ISSUES_FAILURE:
    case CREATE_ISSUE_FAILURE:
      return {
        ...state,
        error: payload,
      };
    default:
      return state;
  }
};

export default issueReducer;
