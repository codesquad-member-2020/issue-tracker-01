import * as api from "Lib/api";
import createRequestThunk from "Lib/createRequestThunk";

/* Read */
export const GET_LABELS = "GET_LABELS";
export const GET_LABELS_SUCCESS = "GET_LABELS_SUCCESS";
export const GET_LABELS_FAILURE = "GET_LABELS_FAILURE";

export const getLabels = createRequestThunk(GET_LABELS, api.getLabels);
