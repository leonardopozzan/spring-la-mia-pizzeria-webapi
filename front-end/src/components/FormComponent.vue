<template>
    <div class="container mt-5 mb-3">
        <button class="btn btn-success" @click="showForm = !showForm"><i class="fa-solid fa-plus"></i></button>
        <div v-if="showForm" class="d-flex justify-content-center">
            <div class="w-75" >
                <form  @submit.prevent="createPizza()" >
                    <div>
                        <label class="form-label" for="name"> Nome </label>
                        <input class="form-control" type="text" id="name" v-model="data.name"  >
                        <ul class="invalid-feedback">
                            <li v-for="(error, i) in errors.name" :key="i">{{ error }}</li>
                        </ul>
                    </div>
                    <div>
                        <label class="form-label" for="price"> Prezzo </label>
                        <input class="form-control" type="number" step="0.01" id="price" v-model="data.price" :class="{ 'is-invalid': errors.price }" >
                        <ul class="invalid-feedback">
                            <li v-for="(error, i) in errors.price" :key="i">{{ error }}</li>
                        </ul>
                    </div>
                    <div>
                        <label class="form-label" for="image"> Immagine </label>
                        <input class="form-control" type="text" id="image" v-model="data.image" :class="{ 'is-invalid': errors.image }" >
                        <ul class="invalid-feedback">
                            <li v-for="(error, i) in errors.image" :key="i">{{ error }}</li>
                        </ul>
                    </div>
                    <div>
                        <label class="form-label" for="description"> Descrizione </label>
                        <input class="form-control" type="text" id="description" v-model="data.description" :class="{ 'is-invalid': errors.description }" >
                        <ul class="invalid-feedback">
                            <li v-for="(error, i) in errors.description" :key="i">{{ error }}</li>
                        </ul>
                    </div>
                    
                    <div class="row text-capitalize">
                        <label class="my-2"> Ingredienti </label>
                        <div class="col-6 col-lg-4 col-xl-3" v-for="(ingredient, i) in ingredientsList" :class="{ 'is-invalid': errors.ingredients }">
                            <input class="form-check-input" type="checkbox" :value="ingredient.id"  v-model="ingredientsSelected">
                            <label class="form-check-label"> {{ ingredient.name }} </label>
                        </div>
                        <ul class="invalid-feedback col-12">
                            <li v-for="(error, i) in errors.ingredients" :key="i">{{ error }}</li>
                        </ul>
                    </div>
                    <div class="mt-3">
                        <button type="submit" class="btn btn-primary me-2" >Crea </button>
                        <button type="reset" class="btn btn-danger" >Reset</button>
                    </div>
                </form>
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
                showForm: true,
                data: {
                    name: '',
                    price: '',
                    image : '',
                    description: '',
                }, 
                errors:{},
                ingredientsList: [],
                ingredientsSelected: []
            }
        },
        methods:{
            createPizza(){
                this.data.ingredients = [];
                for(let i = 0; i < this.ingredientsSelected.length; i++ ){
                    const obj = {
                        id: this.ingredientsSelected[i]
                    }
                    this.data.ingredients.push(obj)
                }
                const send = JSON.stringify(this.data)
                console.log(send)

                axios.post(`${this.apiUrl}/menu/create`, send,{ headers : {'Content-Type': 'application/json'}}).then((response)=>{
                    console.log(response.data)
                    
                }).catch((error)=>{
                    // this.errors= error.response.data.errors
                    console.log(error.response.data.errors)
                })

            },
            getIngredients(){
                axios.get(`${this.apiUrl}/menu/ingredients` ).then((response)=>{
                    console.log(response.data)
                    this.ingredientsList = response.data
                })
            }
        },
        mounted(){
            this.getIngredients()
        }
    }
</script>

<style lang="scss" scoped>

</style>