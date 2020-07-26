import { combineReducers } from "redux";
import labelReducer from "./label/labelReducer";
import loadingReducer from "./loading/loadingReducer";

const rootReducer = combineReducers({
  label: labelReducer,
  loading: loadingReducer,
});

export default rootReducer;
