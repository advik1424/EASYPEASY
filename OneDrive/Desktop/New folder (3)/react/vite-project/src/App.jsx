import React from 'react'
import Student from './component/Student'
import Book from './component/Book'
import paneerbm from'./assets/paneerbm.jpg'

const App = () => {
  return (
    <div>

     
<Student/>
<hr
  style={{
    border: "1px solid white",
    margin: "20px 0",
  }}
/>

{/* ================= Veg Starters ================= */}

<h2 style={{ marginLeft: "20px", color: "green" }}>🥗 Veg Starters</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjOwG6ThQKlFL4TKKVCh6RTWCVnTLUgJCWBFMOv5WnfQ&s=10"
    foodname="Veg Momos"
    type="Veg"
    price="₹120"
  />

  <Book
    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCD_jR0zFCRbUdgVnBSVcFyvLERH1Nm-ZQkvXply6uqQ&s=10"
    foodname="Paneer Tikka"
    type="Veg"
    price="₹280"
  />

  <Book
    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8HysQoThOXdB5a5II-oqZbaYwyQ9MdU-t895zx4Yk7A&s=10"
    foodname="Veg Burger"
    type="Veg"
    price="₹399"
  />

  <Book
    image="https://picsum.photos/300/200?random=11"
    foodname="Veg Sandwich"
    type="Veg"
    price="₹180"
  />
</div>

<hr style={{ border: "1px solid white" }} />

{/* ================= Non Veg Starters ================= */}

<h2 style={{ marginLeft: "20px", color: "red" }}>🍗 Non-Veg Starters</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRT56AlKha3Cmo3ukFpf64H9c0ObNCSiPMt-8jDxm639Q&s=10"
    foodname="Chicken Tikka"
    type="Non Veg"
    price="₹180"
  />

  <Book
    image="https://images.unsplash.com/photo-1544025162-d76694265947?w=300"
    foodname="Grilled Chicken"
    type="Non Veg"
    price="₹450"
  />

  <Book
    image="https://picsum.photos/300/200?random=12"
    foodname="Chicken Shawarma"
    type="Non Veg"
    price="₹399"
  />

  <Book
    image="https://picsum.photos/300/200?random=13"
    foodname="Chicken Lollipop"
    type="Non Veg"
    price="₹299"
  />
</div>

<hr style={{ border: "1px solid white" }} />

{/* ================= Veg Main Course ================= */}

<h2 style={{ marginLeft: "20px", color: "green" }}>🍛 Veg Main Course</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image={paneerbm}
    foodname="Paneer Butter Masala"
    type="Veg"
    price="₹365"
  />

  <Book
    image="https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=300"
    foodname="Masala Dosa"
    type="Veg"
    price="₹180"
  />

  <Book
    image="https://picsum.photos/300/200?random=14"
    foodname="Veg Fried Rice"
    type="Veg"
    price="₹220"
  />

  <Book
    image="https://picsum.photos/300/200?random=15"
    foodname="Cheese Pizza"
    type="Veg"
    price="₹299"
  />
</div>

<hr style={{ border: "1px solid white" }} />

{/* ================= Non-Veg Main Course ================= */}

<h2 style={{ marginLeft: "20px", color: "red" }}>
  🍖 Non-Veg Main Course
</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRT56AlKha3Cmo3ukFpf64H9c0ObNCSiPMt-8jDxm639Q&s=10"
    foodname="Chicken Biryani"
    type="Non Veg"
    price="₹499"
  />

  <Book
    image="https://www.indianhealthyrecipes.com/wp-content/uploads/2021/10/fish-curry-recipe.jpg"
    foodname="Fish Curry"
    type="Non Veg"
    price="₹450"
  />

  <Book
    image="https://picsum.photos/300/200?random=21"
    foodname="Butter Chicken"
    type="Non Veg"
    price="₹420"
  />

  <Book
    image="https://picsum.photos/300/200?random=22"
    foodname="Fish Fry"
    type="Non Veg"
    price="₹390"
  />
</div>

<hr
  style={{
    border: "1px solid white",
    margin: "20px 0",
  }}
/>

{/* ================= Beverages ================= */}

<h2 style={{ marginLeft: "20px", color: "blue" }}>
  🥤 Beverages
</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image="https://picsum.photos/300/200?random=31"
    foodname="Cold Coffee"
    type="Drink"
    price="₹120"
  />

  <Book
    image="https://picsum.photos/300/200?random=32"
    foodname="Mango Shake"
    type="Drink"
    price="₹140"
  />

  <Book
    image="https://picsum.photos/300/200?random=33"
    foodname="Sweet Lassi"
    type="Drink"
    price="₹90"
  />

  <Book
    image="https://picsum.photos/300/200?random=34"
    foodname="Soft Drink"
    type="Drink"
    price="₹60"
  />
</div>

<hr
  style={{
    border: "1px solid white",
    margin: "20px 0",
  }}
/>

{/* ================= Desserts ================= */}

<h2 style={{ marginLeft: "20px", color: "brown" }}>
  🍰 Desserts
</h2>

<div
  style={{
    display: "flex",
    justifyContent: "space-between",
    margin: "5px",
    width: "900px",
    padding: "20px",
  }}
>
  <Book
    image="https://picsum.photos/300/200?random=41"
    foodname="Chocolate Cake"
    type="Dessert"
    price="₹180"
  />

  <Book
    image="https://picsum.photos/300/200?random=42"
    foodname="Brownie"
    type="Dessert"
    price="₹150"
  />

  <Book
    image="https://picsum.photos/300/200?random=43"
    foodname="Ice Cream"
    type="Dessert"
    price="₹100"
  />

  <Book
    image="https://picsum.photos/300/200?random=44"
    foodname="Gulab Jamun"
    type="Dessert"
    price="₹80"
  />
</div>
    
  

    
      
   


 <footer><h3>
              Adress: Ghaziabad Crossing Rebuplic in front of IIT Lalkua</h3></footer>
    </div>
  )
}

export default App