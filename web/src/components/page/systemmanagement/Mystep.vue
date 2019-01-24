<template>
    <div>
        <div class="triangle"></div>
        <ul class="step">
            <li class="active">
                登记个人信息
                <div class="triangle"></div>
            </li>
            <li>
                上传认证资料
                <div class="triangle"></div>
            </li>
            <li>
                <div class="triangle"></div>完成
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "mystep"
    }
</script>

<style scoped>
    ul,li {
        list-style: none;
    }

    .step {
        overflow: hidden;
    }

    .step li {
        position: relative;
        float: left;
        padding: 0 20px;
        line-height: 30px;
        color: #999;
        background: #e3e3e3;
    }

    .triangle {
        content: "";
        position: relative;
        position: absolute;
        top: 0;
        left: 100%;
        z-index: 1;

        border-left: 15px solid #fff;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }

    .triangle:after {
        content: "";
        position: absolute;
        left: -16px;
        top: -15px;

        border-left: 15px solid #e3e3e3;
        border-top: 15px solid transparent;
        border-bottom: 15px solid transparent;
    }

    .step li.active {
        color: #fff;
        background: #ff8308;
    }

    .step li.active .triangle:after {
        border-left-color: #ff8308;
    }
</style>

