import axios from "axios";

export const getLabels = () => axios.get(`${process.env.BASE_URL}labels`);
export const createLabel = (label) => axios.post(`${process.env.BASE_URL}labels`, label);
