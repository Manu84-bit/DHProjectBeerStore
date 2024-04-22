import React from 'react';
import { useKeycloak } from "@react-keycloak/web";
import {useEffect, useState} from 'react';



const Order = () => {
  const { keycloak } = useKeycloak();
  const token = keycloak.token;
  const userId =keycloak.tokenParsed.sub
  const baseUrl = 'http://localhost:50206/api/order/user'
  const [orders, setOrders] = useState([])

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchOrders = async () => {
    setLoading(true); // Start loading before fetching
    setError(null);   // Reset any previous error
  
    try {
      const response = await fetch(`${baseUrl}/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
          // "Access-Control-Allow-Origin": "*",
        },
      });
      if (!response.ok) {
        throw new Error('Failed to fetch orders');
      }
      const data = await response.json();
      setOrders([...data]);
     
    } catch (err) {
      setError(err.message); // Capture the error message
      console.log(error)
    } finally {
      setLoading(false); // End loading after fetch attempt
    }
  };

  // useEffect(() => {
  //  if(userId && token) fetchOrders();
  // }, [userId, token]);
   
  useEffect(() => {
    // console.log('Token:', token);
    // console.log('UserID:', userId);
  
    if (keycloak.authenticated && token && userId) {
      fetchOrders();
    }
  }, [keycloak, token, userId]); // Log key values to trace delays

 console.log(orders)
 return (
   <div>
     <h1 className="text-center my-4">Aquí encontrarás el listado de tus órdenes</h1>
      <div className='my-3 mx-3'>
        {loading ? (
          <p>Loading orders...</p>
        ) : (

          orders?.map((order, index) => (
          <div key={index}>
            <div className="card rounded mx-5 my-2 px-2 w-50" >
              <p>Producto: {order.orderLineItemsDTOList[0].stockCode}</p>
              <p>Cantidad: {order.orderLineItemsDTOList[0].quantity}</p>
              <p>Precio: $ {order.orderLineItemsDTOList[0].price}</p>
            </div>
         
            </div>
          ))
        )}
           <div>
            {orders.length < 1 ? (
           <span className='my-3 mx-3 fs-2'>Aún no has hecho ningún pedido</span>
            ):(
            <div> 
           <span className='my-3 mx-3 fs-2'>Total: </span>
           <span className='mx-3 fs-3'>$ {orders.map((order)=>order.orderLineItemsDTOList[0].price)
            .reduce((accumulator, currentValue) => accumulator + currentValue,
            0,)}</span>
            </div>
          )}
          </div>
      </div>
   </div>
 );
};

export default Order;