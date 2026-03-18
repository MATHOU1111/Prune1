import { BrowserRouter, Route, Routes } from "react-router";


import { useState } from 'react'


function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<h1>Home</h1>} />
            <Route path="/login" element={<h1>About</h1>} />
        </Routes>

    </BrowserRouter>
  )
}

export default App
