import React, { useState } from "react";

function App() {
  const [count, setCount] = useState(0);
  function increment() {
    if (count < 10) {
      setCount(count + 1);
    }
  }
  function decrement() {
    if (count > 0) {
      setCount(count - 1);
    }
  }
  function reset() {
    setCount(0);
  }
  return (
    <>
      <button onClick={increment}>Increment</button>
      <p>{count}</p>
      <button onClick={decrement}>Decrement</button>
      <button onClick={reset}>Reset</button>
      {count === 10 && <p>Limit exceeded</p>}
    </>
  );
}
export default App;
