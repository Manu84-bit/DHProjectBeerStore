import React, { createContext, useState } from 'react';

export const CategoryContext = createContext();

export const CategoryListProvider = ({ children }) => {
  const [categories, setCategories] = useState([]);
  const [categoryNameC, setCategoryName] = useState("");

  const updateCategories = (categories) => {
    setCategories(categories)
  }

  

  const updateCategoryName = (categoryNameC) => {
    setCategoryName(categoryNameC);
  }


  return (
    <CategoryContext.Provider value={{ categories, categoryNameC, updateCategories, updateCategoryName }}>
      {children}
    </CategoryContext.Provider>
  );
}