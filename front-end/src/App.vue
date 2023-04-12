<template>

  <header>
    <HeaderComponent/>
  </header>
  <main>
    <MenuComponent />
  </main>
</template>

<script>
import HeaderComponent from './components/HeaderComponent.vue';
import MenuComponent from './components/MenuComponent.vue';


  export default {
    components: { HeaderComponent , MenuComponent}
}
</script>

<style lang="scss" scoped>

</style>