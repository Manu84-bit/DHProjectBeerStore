import React from 'react';
import {useContext, useEffect, useState, useRef} from 'react';
import { ProductContext } from "../context/productContext";
import { getProducts } from "../api/apiProduct";
import ProductCard from '../components/ProductCard';
import { CategoryContext } from '../context/categoryContext';
import { useKeycloak } from "@react-keycloak/web";

export default function ProductList() {
  const input = useRef()
  const { products, updateProducts } = useContext(ProductContext);
  const { categoryNameC, updateCategoryName } = useContext(CategoryContext);
  const [brand, setBrand] = useState("");
  const [searched, setSearched] = useState(false);
  const { keycloak } = useKeycloak();



  const [quantity, setQuantity] = useState(0)
  const [sku, setSku] = useState("")
  const [price, setPrice] = useState("")

  
  const createOrder = async (e) => {
   
    if(quantity > 0) {
      const token = keycloak.token;
      const userId = keycloak.tokenParsed.sub
      const postUrl = 'http://localhost:50206/api/order/new'
      // console.log("Autorización " + token);
        await fetch(postUrl, {
             method: "POST",
             body: JSON.stringify({
              "userId":`${userId}`,
              "orderLineItemsDTOList":[
          {
              "stockCode":sku,
              "price": price,
              "quantity":quantity
          }
              ]
          }),
             headers: {
               Accept: "application/json",
               "Content-Type": "application/json",
               Authorization: `Bearer ${token}`,
             },
           })
             .then((res) => res.json())
             .then((data) => {
               console.log("Orden agregada");
             })
             .catch((err) => console.error(err));
             
           e.preventDefault();
         } else {
          console.log("Defina una cantidad")
         }


    }
    

    const handleChange = (e) => {
       let {id, name, value } = e.target;
        let pricein = parseFloat(name)
        let quantityin = parseInt(value)
        setQuantity(quantityin)
        console.log(quantity)
        setSku(id)
        console.log(id)
        setPrice(pricein * quantityin)
        console.log(name)
    
      }



 
  useEffect(() => {
    async function fetchData() {
      try {
        const products = await getProducts();
        updateProducts(products);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    }

    fetchData();
    console.log("category is " + categoryNameC);
  }, []);

  
  const handleButton = async (e)=>{
    e.preventDefault();
    setSearched(true)
    try{
      await updateCategoryName("");
     
    }catch (e){
      throw e;
    }
    console.log(brand)
  }


  return(
    <div className='container'>
      <div className='container'>
        <h1 className="text-center mt-3">Elige tus cervezas favoritas</h1>
        <form className="d-flex justify-content-center my-3" >
          <input className="form-control mr-sm-2 w-50 mx-2" type="search" placeholder="Busca por marca" onChange={()=>setBrand(input.current.value)} aria-label="Search" ref={input}/>
          <button className="btn btn-warning my-2 my-sm-0" onClick={(e)=>handleButton(e)}>Buscar</button>
        </form>
      </div>
      <div className='d-flex flex-wrap justify-content-evenly'>
    {
      // !!brand=="" ||
       !searched? (!!categoryNameC==""? products:products.filter(product=>product.categoryName===categoryNameC))
       .map(product => <ProductCard
         key={product.productID}
         nameP={product.name} 
         description={product.description} 
         imageURL={product.imageURL} 
         price={product.price}
         categoryName={product.categoryName}
         handleChange={handleChange}
         addOrder={createOrder}
         />
        ):

        products.filter(product=>product.name.toLowerCase().includes(brand.toLowerCase()))
        .map(product => <ProductCard
          key={product.productID} 
          nameP={product.name} 
          description={product.description} 
          imageURL={product.imageURL} 
          price={product.price}
          categoryName={product.categoryName}
          handleChange={handleChange}
          // addOrder={}
          />)
         }

      </div>
      </div>

  );

}
