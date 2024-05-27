import './App.scss';
import { Routes, Route } from 'react-router-dom';
import List from './pages/List';
import Create from './pages/Create';
import Update from './pages/Update';
import NotFound from './pages/NotFound';
import DataTable from './components/Demo';
import NotFoundPage from './pages/NotFoundPage';

function App() {
  return (
    <Routes>
      <Route path="/" element={<List />} />
      <Route path="/create" element={<Create />} />
      <Route path="/update/:id" element={<Update />} />
      <Route path='*' element={<NotFoundPage />} />
      <Route path="/demo" element={<DataTable />} />
    </Routes>
  );
}

export default App;
