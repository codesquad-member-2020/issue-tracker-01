import { combineReducers } from "redux";
import labelReducer from "./label/labelReducer";

const rootReducer = combineReducers({
  label: labelReducer,
});

export default rootReducer;
