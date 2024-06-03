import React, { useState, useEffect } from 'react';

const usePagination = (data, itemsPerPage) => {
    const [currentPage, setCurrentPage] = useState(1);
    const [key, setKey] = useState(0); // Key for resetting pagination state

    useEffect(() => {
        setCurrentPage(1); // Reset current page to 1 when data changes
        setKey((prevKey) => prevKey + 1); // Reset key to trigger re-render
    }, [data]);

    const totalPages = Math.ceil(data.length / itemsPerPage);

    const currentData = () => {
        const begin = (currentPage - 1) * itemsPerPage;
        const end = begin + itemsPerPage;
        return data.slice(begin, end);
    };

    const next = () => {
        setCurrentPage((currentPage) => Math.min(currentPage + 1, totalPages));
    };

    const prev = () => {
        setCurrentPage((currentPage) => Math.max(currentPage - 1, 1));
    };

    return { next, prev, currentData, currentPage, totalPages, key };
};


const CustomTable = ({ data, itemsPerPage, columns }) => {
    const { next, prev, currentData, currentPage, totalPages, key } = usePagination(data, itemsPerPage);

    return (
        <div key={key}>
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        {columns.map((column) => (
                            <th key={column.key}>{column.header}</th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {currentData().map((row, index) => (
                        <tr key={row.id}>
                            <td>{(currentPage - 1) * itemsPerPage + index + 1}</td>
                            {columns.map((column) => (
                                <td key={column.key}>{column.render(row)}</td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
            {data.length === 0 && <p style={{textAlign: "center"}}>Không có kết quả</p>}
            {console.log(data) }
            <div>
                {totalPages > 1 && (
                    <div>
                        <button onClick={prev} disabled={currentPage === 1} className="btn prev">
                            Trước
                        </button>
                        <span>
                            Trang {currentPage} / {totalPages}
                        </span>
                        <button
                            onClick={next}
                            disabled={currentPage === totalPages || totalPages === 0}
                            className="btn next"
                        >
                            Sau
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
};


export default CustomTable;
