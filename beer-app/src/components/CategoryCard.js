import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { CategoryContext } from '../context/categoryContext';
import {useContext, useEffect} from 'react';
import { useNavigate } from "react-router-dom"

const CategoryCard = ({categoryName, categoryDescription, imgUrl}) => {

  const { categoryNameC, updateCategoryName } = useContext(CategoryContext)
  const navigate = useNavigate()

   async function handleButton(cat) {
    try {
     await updateCategoryName(cat)
    } catch (e) {
      throw e
    }
    navigate("/products")
   }

    return (
        <Card style={{ width: '22rem', flexDirection:'row', marginBlock:'2rem', marginInline:'2rem', boxShadow: 'rgba(149, 157, 165, 0.2) 0px 8px 24px'}}>
          <Card.Img variant="top" src={imgUrl} style={{ width: '40%', height: 'auto', objectFit:'contain'}}/>
          <Card.Body style={{ paddingInline: '1rem', width:'60%'}}>
            <Card.Title>{categoryName}</Card.Title>
            <Card.Text >
             {categoryDescription}
            </Card.Text>
            <Button variant="primary" style={{ marginTop: '1rem'}} onClick={()=>handleButton(categoryName)} >Ver cervezas</Button>
          </Card.Body>
        </Card>
      );

}

export default CategoryCard;