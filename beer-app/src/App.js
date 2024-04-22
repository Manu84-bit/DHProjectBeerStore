
import './App.css';
import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import HomePage from "./pages/HomePage";
import ProductPage from "./pages/ProductPage";
import OrderPage from "./pages/OrderPage";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./keycloak";
import PrivateRoute from "./helpers/privateRoute";
import { ProductListProvider } from './context/productContext';
import { CategoryListProvider } from './context/categoryContext';

function App() {

 
  return (
    <div>
      <ReactKeycloakProvider authClient={keycloak} keycloak={keycloak}>
      <Nav />
      <CategoryListProvider>
      <ProductListProvider>
     <BrowserRouter>
    
       <Routes>
         <Route exact path="/" element={<HomePage />} />
      
         <Route exact path="/products" element={<ProductPage />} />
       
         <Route path="/orders" element={
          <PrivateRoute>
            <OrderPage/>
          </PrivateRoute>
         } />
       </Routes>
      
     </BrowserRouter>
     </ProductListProvider>
     </CategoryListProvider>
     </ReactKeycloakProvider>
     </div>
  );
}

export default App;
