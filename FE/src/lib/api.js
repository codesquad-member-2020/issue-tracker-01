import axios from "axios";

export const getLabels = () => axios.get(`${process.env.BASE_URL}labels`);
