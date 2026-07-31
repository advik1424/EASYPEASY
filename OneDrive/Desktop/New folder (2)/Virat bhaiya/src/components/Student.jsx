import React from 'react'
import logo from './img.jpg'
const Student = ({ name, branch, rollno }) => {
  return (
    <div style={{ backgroundColor:'pink', border:'12px solid black'}}>




<center style={{padding:'50px'}}><div style={{ border:'15px solid green', width :'350px',height:'350px',padding:'50px'}}>
  <h4 style={{ color:'red',textAlign:'center'}}> Student Card</h4>
    <h4>  <img style={{ width:'50px', height:'50px'}} src={logo} alt="Student Photo" /></h4>
    <p style={{ color:'green',textAlign:'center'}}>Name:{name} </p>
   

    <p style={{ color:'green',textAlign:'center'}}>Branch:{branch} </p>
    <p style={{ color:'green',textAlign:'center'}}>Roll no:{rollno} </p>
 

</div></center>
      

    </div>
  )
}

export default Student