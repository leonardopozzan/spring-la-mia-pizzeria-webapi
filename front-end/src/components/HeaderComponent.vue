<template>
    <nav class="navbar navbar-expand-lg  navbar-dark bg-dark  d-flex justify-content-between">

  <div class="container">
      
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 d-flex" @submit.prevent="filterMenu()">
          <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
  </div>
 
</nav>
</template>

<script>
import {store} from '../store';
    export default {
        data(){
            return{
                search: ''
            }
        },
        methods:{
            filterMenu(){
                store.search = this.search
          
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>