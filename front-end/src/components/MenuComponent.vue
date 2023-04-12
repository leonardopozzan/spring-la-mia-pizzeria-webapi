<template>
    <div class="container">
        <div class="d-flex flex-wrap mt-5">
            <div class="card col-3"  v-for="(pizza, i) in menu" :key="i">
                <img v-if="pizza.image" class="card-img-top" :src="`${pizza.image}`" alt="Card image cap">
                <img v-else src="https://picsum.photos/id/63/1920/1080" alt="">
                <div class="card-body">
                    <h5 class="card-title">{{ pizza.name }}</h5>
                    <p class="card-text"><span v-for="(ingredient, j) in pizza.ingredients" :key="j">{{ ingredient.name }}&nbsp;</span></p>
                    <a href="#" class="btn btn-primary">Dettagli</a>
                </div>
            </div>
        
        </div>
    </div>
</template>

<script>
import {store} from '../store';
import axios from 'axios';
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