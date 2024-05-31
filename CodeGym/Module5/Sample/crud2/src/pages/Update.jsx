import { useForm } from 'react-hook-form';
import { toast } from "react-toastify";
import {useLocation, useNavigate} from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';
import { ClipLoader } from 'react-spinners';
import * as ClassesService from '../services/ClassesService';
import * as StudentService from '../services/StudentService';

function Update(props) {
    const location = useLocation();
    const [dataUpdate, setDataUpdate] = useState({});
    const navigate = useNavigate();
    const {register, handleSubmit, setValue, errors} = useForm();
    const [classes, setClasses] = useState([]);

    useEffect(() => {
        try {
            findById(location.state?.idUpdate)
        }catch(error) {
            console.error(error);
        }
    }, []);

    useEffect(() => {
        console.log(dataUpdate)
        if (dataUpdate) {
            const { id, code, name, age, gender,dateNH, class: studentClass } = dataUpdate;
            setValue('id',id)
            setValue('code', code);
            setValue('name', name);
            setValue('age', age);
            setValue('gender', gender);
            setValue('dateNH', dateNH);
            setValue('class', JSON.stringify(studentClass));
        }
        getAll()
    }, [dataUpdate]);

    const getAll =  async ()=>{
        const data = await ClassesService.getAll()
        setClasses(data);
    }

    const findById = async (id) => {
        try{
            const data = await  StudentService.findById(id)
            setDataUpdate(data)
        }catch (err){
            console.log(err)
        }
    }

    const onSubmit = async (data) => {
        try {
            data.class = JSON.parse(data.class);
            console.log(data);
            await StudentService.update(data.id,data);
            console.log(data);
        } catch (e) {
            toast.error("Sửa thất bại")
            console.log(e)
            return;
        }
        toast.success("Sửa thành công")
        navigate("/")
    };

    if (!dataUpdate?.id) return (
        <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)' }}>
          <ClipLoader color={"#123abc"} loading={true} size={150} />
        </div>
      );

    return (
        <div className='wrapper'>
            <Sidebar />
            <div className='main'>
                <Header />
                <div className='container'>
                    <div className='add-item'>
                        <h2>Sửa dữ liệu</h2>
                        <form className='form' onSubmit={handleSubmit(onSubmit)}>
                            <input type="hidden" name="id" defaultValue={dataUpdate.id}/>
                            <input {...register('code')} type="text" placeholder="Nhập mã học viên" />
                            <input {...register('name')} type="text" placeholder='Nhập tên học viên' />
                            <input {...register('age')} type="text" placeholder='Nhập tuổi' />
                            <label htmlFor="">Giới tính:</label>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nam' type="radio" /><span>Nam</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Nữ' type="radio" /><span>Nữ</span>
                            <input {...register('gender',{required:'Không được để trống!'})} style={{display:"inline",width:"20%"}} value='Khác' type="radio" /><span>Khác</span>
                            <input {...register('dateNH')} type="date" placeholder='Nhập ngày nhập học' />
                            <select {...register('class',{required: true})} id="" >
                                <option value="">-- Lựa chọn lớp học --</option>
                                {
                                    classes.map((item) =>  (
                                        <option value={JSON.stringify(item)} key={item.id} selected={item.id === dataUpdate.class.id}>{item.code}</option>
                                    ))
                                }
                            </select>
                            <button type="submit">Gửi</button>
                        </form>
                    </div>
                    <button onClick={() => navigate("/")} className='btn back'>Quay lại</button>
                </div>
            </div>
        </div>
    );
}

export default Update;