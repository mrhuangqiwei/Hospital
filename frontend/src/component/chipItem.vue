<style lang="scss" scoped>
    div.name{
        margin-top: 0.5rem;
    }
</style>

<template>
    <div class="chipItem" @click='oneClick'>
        <div :class='iconName'></div>
        <div class='name'>{{name}}</div>
        <slot name='dic'></slot>
    </div>
</template>

<script>
    export default {
        props:['iconName','name','doClick'],
        data: function () {
            return {
               
            }
        },
        methods:{
            oneClick(){
                if(typeof this.doClick == 'function'){
                    this.doClick();
                }
            }
        }
    }
</script>