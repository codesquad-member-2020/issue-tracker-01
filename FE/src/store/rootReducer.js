import { combineReducers } from "redux";
import issueReducer from "./issue/issueReducer";
import labelReducer from "./label/labelReducer";
import loadingReducer from "./loading/loadingReducer";

const rootReducer = combineReducers({
  issue: issueReducer,
  label: labelReducer,
  loading: loadingReducer,
});

export default rootReducer;
