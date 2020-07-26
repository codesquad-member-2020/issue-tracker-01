export const START_LOADING = "START_LOADING";
export const FINISH_LOADING = "FINISH_LOADING";

export const startLoading = (requestType) => ({ type: START_LOADING, payload: requestType });
export const finishLoading = (requestType) => ({ type: FINISH_LOADING, payload: requestType });
