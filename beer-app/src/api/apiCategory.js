import axios from 'axios';

const baseURL = "http://localhost:8080/api/category"
export const getCategories = async () => {
  try {
    const response = await axios.get(baseURL);
    return response.data;
  } catch (error) {
    throw error;
  }
}


