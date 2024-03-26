<template>
    <div  style="display:flex;height: 80px; width: 400px; margin-bottom: 30px; margin-right: 10px;">
            <img style="width: 80px;" src="../assets/img/日历.png" alt="">
            <div style="display: inline-block;font-size: 15px; color: #606266; height: 60px;width: 350px;margin:10px;" for="">{{ notice }}</div>
    </div>
</template>

<script>
export default {
    props: {
        notice: {
            type: String,
            required: true
        }
    }
};

</script>