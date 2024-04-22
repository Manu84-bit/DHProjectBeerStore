import React from "react";
import { useKeycloak } from "@react-keycloak/web";
import {FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faCartPlus} from "@fortawesome/free-solid-svg-icons";
import "bootstrap/js/src/collapse.js";



const Nav = () => {
  const { keycloak, initialized } = useKeycloak();


 return (
<nav className="navbar navbar-expand-lg navbar-light shadow py-3" style={{backgroundColor:"#E7BB41"}}>
<div className="container d-flex justify-content-center">
  <a className="navbar-brand px-3" href="/"><span className="h3">DH Beer Store</span> </a>
  <button className="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
   <div className="collapse navbar-collapse" id="navbarNav">
   <ul className="navbar-nav ms-auto mx-5 h5">
      <li className="nav-item active ">
        <a className="nav-link" href="/">Inicio<span className="sr-only"></span></a>
      </li>
      <li className="nav-item active">
        <a className="nav-link" href="/products">Productos<span className="sr-only"></span></a>
      </li>
      <li className="nav-item active">
        <a className="nav-link" href="/orders">
          <FontAwesomeIcon icon={faCartPlus}/>
        </a>
      </li>
    </ul>
    <div className="d-grid gap-2 d-md-flex justify-content-end ">
               <div className="mx-0">
                 {!keycloak.authenticated && (
                  <div>
                   <button
                     type="button"
                     className="btn btn-outline-dark"
                     onClick={() => keycloak.login()}
                   >
                     Login
                   </button>
                   <button
                     type="button"
                     className="btn btn-outline-dark mx-3"
                     onClick={() => keycloak.register()}
                   >
                     Registro
                   </button>
                   </div>
                   
                 )}

                 {!!keycloak.authenticated && (
                   <button
                     type="button"
                     className="btn btn-outline-secondary"
                     onClick={() => keycloak.logout()}
                   >
                     Logout ({keycloak.tokenParsed.preferred_username})
                     {/* Logout ({keycloak.tokenParsed.sub}) */}

                   </button>
                 )
                 }
               </div>
             </div>
  </div>
  </div>
</nav>
 );
};

export default Nav;