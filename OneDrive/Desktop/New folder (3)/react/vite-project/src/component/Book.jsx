import React from 'react'

const Book = (props) => {
  return (
    <>
    
     <div class="container" style={{margin:'4px',width :'900px',padding:'10px'}}>
              <div class="card" style={{ width:'150px',padding:'6px', border:'3px solid red'}}>

                <img src={props.image} alt="foodimage" style={{
  width: "100%",
  height: "150px",
  objectFit: "cover",
  borderRadius: "8px"
}}/>

            <h2>{props.foodname}</h2>

            <p>{props.type}</p>

            <h3>{props.price}</h3>

            <button>Add to Cart</button>




             </div>

              </div>
</>
    
  )
}

export default Book