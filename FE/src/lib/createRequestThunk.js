import { startLoading, finishLoading } from "Store/loading/loadingAction";

export default function createReqeustThunk(type, request) {
  const SUCCESS = `${type}_SUCCESS`;
  const FAILURE = `${type}_FAILURE`;

  return (params) => async (dispatch) => {
    dispatch({ type });
    dispatch(startLoading(type));
    try {
      const response = await request(params);
      console.log("response : ", response, "params : ", params);
      dispatch({ type: SUCCESS, payload: params || response });
    } catch (e) {
      dispatch({ type: FAILURE, payload: e });
      throw e;
    }
    dispatch(finishLoading(type));
  };
}
