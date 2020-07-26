import * as api from "Lib/api";
import createRequestThunk from "Lib/createRequestThunk";

/* Read */
export const GET_LABELS = "GET_LABELS";
export const GET_LABELS_SUCCESS = "GET_LABELS_SUCCESS";
export const GET_LABELS_FAILURE = "GET_LABELS_FAILURE";

export const getLabels = createRequestThunk(GET_LABELS, api.getLabels);

/* Create */
export const CREATE_LABEL = "CREATE_LABEL";
export const CREATE_LABEL_SUCCESS = "CREATE_LABEL_SUCCESS";
export const CREATE_LABEL_FAILURE = "CREATE_LABEL_FAILURE";

export const createLabel = createRequestThunk(CREATE_LABEL, api.createLabel);

/* Update */
export const EDIT_LABEL = "EDIT_LABEL";
export const EDIT_LABEL_SUCCESS = "EDIT_LABEL_SUCCESS";
export const EDIT_LABEL_FAILURE = "EDIT_LABEL_FAILURE";

export const editLabel = createRequestThunk(EDIT_LABEL, api.editLabel);

/* Delete */
export const DELETE_LABEL = "DELETE_LABEL";
export const DELETE_LABEL_SUCCESS = "DELETE_LABEL_SUCCESS";
export const DELETE_LABEL_FAILURE = "DELETE_LABEL_FAILURE";

export const deleteLabel = createRequestThunk(DELETE_LABEL, api.deleteLabel);
