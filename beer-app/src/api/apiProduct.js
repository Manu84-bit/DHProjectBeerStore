import axios from 'axios';

const baseURL = "http://localhost:8080/api/product"
export const getProducts = async () => {
  try {
    const response = await axios.get(baseURL);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const createProduct = async (product) => {
  try {
    const response = await axios.post(baseURL + "/new", product);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const getProductById = async (id) => {
  try {
    const response = await axios.get(baseURL + `/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const updateProductById = async (id, product) => {
  try {
    const response = await axios.put(baseURL`/ + ${id}`, product);
    return response.data;
  } catch (error) {
    throw error;
  }
}

export const deleteProductById = async (id) => {
  try {
    const response = await axios.delete(baseURL + `/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}