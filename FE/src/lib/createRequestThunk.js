import { startLoading, finishLoading } from "Store/loading/loadingAction";

export default function createReqeustThunk(type, request) {
  const SUCCESS = `${type}_SUCCESS`;
  const FAILURE = `${type}_FAILURE`;

  return (params) => async (dispatch) => {
    dispatch({ type });
    dispatch(startLoading(type));
    try {
      const { data } = await request(params);
      dispatch({ type: SUCCESS, payload: data });
    } catch (e) {
      dispatch({ type: FAILURE, payload: e });
      throw e;
    }
    dispatch(finishLoading(type));
  };
}
