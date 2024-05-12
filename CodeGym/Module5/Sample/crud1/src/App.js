import './App.css';
import { Routes, Route } from 'react-router-dom';
import List from './components/List';
import Create from './components/Create';
import Update from './components/Update';
import AddOrder from './components/AddOrder';

function App() {
  return (
    <Routes>
      <Route path="/" element={<List />} />
      <Route path="/create" element={<Create />} />
      <Route path='/add' element={<AddOrder />} />
      <Route path="/update" element={<Update />} />
    </Routes>
  );
}

export default App;
