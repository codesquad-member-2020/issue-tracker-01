import axios from "axios";

/* Issue */
export const getIssues = () => axios.get(`${process.env.BASE_URL}issues`);
export const createIssue = (issue) => axios.post(`${process.env.BASE_URL}issues`, issue);

/* Label */
export const getLabels = () => axios.get(`${process.env.BASE_URL}labels`);
export const createLabel = (label) => axios.post(`${process.env.BASE_URL}labels`, label);
export const editLabel = ({ labelId, updatedValues }) => {
  axios.put(`${process.env.BASE_URL}labels/${labelId}`, updatedValues);
};
export const deleteLabel = (labelId) => axios.delete(`${process.env.BASE_URL}labels/${labelId}`);
