import * as api from "Lib/api";
import createRequestThunk from "Lib/createRequestThunk";

/* Read */
export const GET_ISSUES = "GET_ISSUES";
export const GET_ISSUES_SUCCESS = "GET_ISSUES_SUCCESS";
export const GET_ISSUES_FAILURE = "GET_ISSUES_FAILURE";

export const getIssues = createRequestThunk(GET_ISSUES, api.getIssues);

/* Create */
export const CREATE_ISSUE = "CREATE_ISSUE";
export const CREATE_ISSUE_SUCCESS = "CREATE_ISSUE_SUCCESS";
export const CREATE_ISSUE_FAILURE = "CREATE_ISSUE_FAILURE";

export const createIssue = createRequestThunk(CREATE_ISSUE, api.createIssue);
