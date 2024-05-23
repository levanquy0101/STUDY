import React from 'react';
import CustomTable from '../hooks/useTable';

const DataTable = () => {
    const data = [
        { id: 1, name: 'Item 1', age: 20 },
        { id: 2, name: 'Item 2', age: 25 },
        { id: 3, name: 'Item 3', age: 30 },
        { id: 4, name: 'Item 4', age: 35 },
        { id: 5, name: 'Item 5', age: 40 },
        { id: 6, name: 'Item 6', age: 45 },
        { id: 7, name: 'Item 7', age: 50 },
        { id: 8, name: 'Item 8', age: 55 },
        { id: 9, name: 'Item 9', age: 60 },
        { id: 10, name: 'Item 10', age: 65 },
        { id: 11, name: 'Item 11', age: 70 },
        { id: 12, name: 'Item 12', age: 75 },
        { id: 13, name: 'Item 13', age: 80 },
        { id: 14, name: 'Item 14', age: 85 },
        { id: 15, name: 'Item 15', age: 90 },
        // Add more data items as needed
      ];
      
  const columns = [
    { header: 'ID', key: 'id', render: (row) => row.id },
    { header: 'Name', key: 'name', render: (row) => row.name },
    { header: 'Age', key: 'age', render: (row) => row.age },
    // Add more columns as needed
  ];

  return <CustomTable data={data} itemsPerPage={100} columns={columns} />;
};

export default DataTable;
