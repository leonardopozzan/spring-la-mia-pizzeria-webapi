<template>
    <div class="container">
        <div class="d-flex flex-wrap">
            <div class="card col-3"  v-for="(pizza, i) in menu" :key="i">
                <img v-if="pizza.image" class="card-img-top" :src="`${pizza.image}`" alt="Card image cap">
                <img v-else class="card-img-top" src="https://picsum.photos/id/63/1920/1080" alt="">
                <div class="card-body">
                    <h5 class="card-title">{{ pizza.name }}</h5>
                    <p class="card-text"><span v-for="(ingredient, j) in pizza.ingredients" :key="j">{{ ingredient.name }}&nbsp;</span></p>
                    <button @click="deletePizza(pizza.id)" class="btn btn-danger"><i class="fa-solid fa-trash-can"></i></button>
                </div>
            </div>
        
        </div>
    </div>
</template>

<script>
import {store} from '../store';
import axios from 'axios';
import Swal from 'sweetalert2'
    export default {
        data(){
            return{
                apiUrl : 'http://127.0.0.1:8080/api/v1',
                menu: [],
                store,
            }
        }, 
        methods: {
            getAllPizzas(){
                const data = {
                    params: {
                        name : store.search
                    }
                }
                axios.get(`${this.apiUrl}/menu`, data, ).then((response)=>{
                    console.log(response.data)
                    this.menu = response.data
                })
            }, deletePizza(id){
                axios.delete(`${this.apiUrl}/menu/${id}`).then((response)=>{
                    console.log(response.data)
                    if(response.data.success){
                        this.alertSuccess()
                        this.getAllPizzas()
                    }
                }).catch((error, response)=>{
                    console.log(error)
                    this.alertError(error.response.data.message)
                })
            },
            alertSuccess(){
                Swal.fire({
                            position: 'top',
                            icon: 'success',
                            title: 'La pizza è stata eliminata',
                            showConfirmButton: false,
                            timer: 1500
                        })
            },
            alertError(message){
                Swal.fire({
                            position: 'top',
                            icon: 'error',
                            title: message,
                            showConfirmButton: false,
                            timer: 1500
                        })
            }
        },
        watch:{
            'store.search'(){
                this.getAllPizzas();
            }
            
        },
        mounted(){
            this.getAllPizzas()
        }
    }
</script>

<style lang="scss" scoped>

</style>