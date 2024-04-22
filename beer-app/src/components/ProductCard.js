import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


const ProductCard = ({nameP, description, imageURL, price, categoryName, addOrder, handleChange}) => {

        return (
        <Card style={{ width: '20rem', marginBlock:'2rem', marginInline:'1rem', boxShadow: 'rgba(149, 157, 165, 0.2) 0px 8px 24px'}}>
          <Card.Img variant="top" src={imageURL} style={{ width: '100%', height: '18rem', objectFit:'contain', paddingTop:'2rem'}}/>
          <Card.Body style={{ paddingInline: '2rem'}}>
            <Card.Title>{nameP}</Card.Title>
            <Card.Text >
             {description}
            </Card.Text>
            <Card.Subtitle style={{ marginTop: '1rem'}}>
             Tipo: {categoryName}
            </Card.Subtitle>
            <Card.Subtitle style={{ marginTop: '1rem'}}>
             ${price}
            </Card.Subtitle>
            <input type="number"  id={nameP} name={price} className="form-control" style={{ width: '7rem', marginTop:'2rem'}} placeholder="cantidad" min={0} onClickCapture={(e)=>handleChange(e)} ></input>
            <Button onClick={(e)=>addOrder(e)} variant="primary" style={{ marginTop: '1rem'}}>Enviar a carrito</Button>
          </Card.Body>
        </Card>
      );

}

export default ProductCard;