import axios from "axios";

export const FETCH_LABELS_REQUEST = "FETCH_LABELS_REQUEST";
export const FETCH_LABELS_SUCCESS = "FETCH_LABELS_SUCCESS";
export const FETCH_LABELS_FAILURE = "FETCH_LABELS_FAILURE";

export const fetchLabelsRequest = () => ({ type: FETCH_LABELS_REQUEST });
export const fetchLabelsSuccess = (labels) => ({ type: FETCH_LABELS_SUCCESS, payload: labels });
export const fetchLabelsFailure = (error) => ({ type: FETCH_LABELS_FAILURE, payload: error });

export const fetchLabels = () => async (dispatch) => {
  dispatch(fetchLabelsRequest());
  try {
    const {
      data: { labels },
    } = await axios.get(process.env.BASE_URL + "labels");
    dispatch(fetchLabelsSuccess(labels));
  } catch (error) {
    dispatch(fetchLabelsFailure(error));
  }
};
