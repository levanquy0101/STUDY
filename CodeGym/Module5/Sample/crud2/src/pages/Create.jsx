import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
// import * as ClassesService from '../services/ClassesService';
// import * as StudentService from '../services/StudentService';
import * as ProductService from '../services/ProductService';
import * as OrderService from "../services/OrdersService";

function Create(props) {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm()
    const [products, setProducts] = useState([]);


    useEffect(() => {
        getAll()
    }, []);
    const getAll =  async ()=>{
        const data = await ProductService.getAll()
        setProducts(data);
    }

    const onSubmit = async (data) => {
        try {
            data.product = JSON.parse(data.product);
            data.total = data.number * data.product.price;
            await OrderService.save(data);
            console.log(data);
        } catch (e) {
            toast.error("Thêm mới thất bại")
            console.log(e)
            return;
        }
        toast.success("Thêm mới thành công")
        navigate("/")
    };
    const validateDate = value => new Date(value) < new Date().setHours(0, 0, 0, 0) || 'Ngày mua phải trước ngày hiện tại';

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Tạo mới</h2>
                        <form className='form' onSubmit={handleSubmit(onSubmit)}>
                            <input {...register('code',{required: 'Không được để trống!'})} type="text" placeholder="Nhập mã đơn hàng"/>
                            {errors.code && <small>{errors.code.message}</small>}

                            <input {...register('number', { required: "Không được để trống!", min:{ value: 1, message:"Số phải lớn hơn 0"}, pattern: {value: /^[1-9]\d*$/, message: "Phải là số nguyên!"}})}placeholder='Nhập số lượng' type='number' />
                            {errors.number && <small>{errors.number.message}</small>}

                            <input {...register('buyDate',{required:'Không được để trống!', validate: validateDate})} type="date" placeholder='Nhập ngày mua' />
                            {errors.buyDate && <small>{errors.buyDate.message}</small>}

                            <input {...register('total')} type="hidden" placeholder='Nhập số lượng'/>
                            
                            <select {...register('product',{required: 'Không được để trống!'})} id="">
                                <option value="" >-- Lựa chọn sản phẩm --</option>
                                {
                                    products.map((item) =>  (
                                        <option value={JSON.stringify(item)} key={item.id}>{item.name}</option>
                                    ))
                                }
                            </select>
                            {errors.product && <small>{errors.product.message}</small>}
                            <button type="submit">Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Create;