import React from 'react';
import {useContext, useEffect} from 'react';
import CategoryCard from '../components/CategoryCard';
import { getCategories } from "../api/apiCategory";
import { CategoryContext } from '../context/categoryContext';
import 'bootstrap/dist/css/bootstrap.min.css';


const Home = () => {
  const { updateCategoryName, categories, updateCategories } = useContext(CategoryContext);
  
    
  useEffect(() => {
    updateCategoryName("")
    async function fetchData() {
      try {
        const categories = await getCategories();
        updateCategories(categories);
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    }

    fetchData();
  }, []);

 return (
   <div className="img-fluid">
     <div className='bg-img w-100'>
      {/* <img  className='w-100 h-auto' src='https://images.lifestyleasia.com/wp-content/uploads/sites/7/2023/06/06212441/Low-calorie-beers-4-1600x900.jpg' alt='beer'></img> */}
      <h1 className="text-start fs-1 mx-5 anim my-4">Bienvenid@ a DH Beer Store</h1>
     </div>
     <h2 className='text-center mt-5'>Nuestras categorías</h2>
     <div className='d-flex flex-wrap justify-content-center'>
     {categories.map(category => <CategoryCard
         key={category.categoryID} 
         categoryName={category.categoryName}
         categoryDescription={category.categoryDescription} 
         imgUrl={category.imgUrl} 
         />)}
     </div>
   </div>
 );
};

export default Home;