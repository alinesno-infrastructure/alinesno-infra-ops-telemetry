<template>
    <div>
        <el-row class="acp-dashboard-panel" :gutter="20">

            <el-col class="panel-col" :span="16">
                <div class="grid-content">
                    <div class="panel-header">
                        <div class="header-title"><i class="fa-solid fa-link"></i> 最新运行统计</div>
                    </div>
                    <div class="panel-body acp-height-auto">

                        <div class="count-box">
                            <ul>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">接入应用数</div>
                                    <div class="count-box-item-number">280</div>
                                </li>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">接入应用数</div>
                                    <div class="count-box-item-number">28</div>
                                </li>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">TranSpan数</div>
                                    <div class="count-box-item-number">22376190</div>
                                </li>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">异常数</div>
                                    <div class="count-box-item-number">280</div>
                                </li>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">TranSpan数</div>
                                    <div class="count-box-item-number">192318873</div>
                                </li>
                                <li class="count-box-item">
                                    <div class="count-box-item-title">异常数</div>
                                    <div class="count-box-item-number">280</div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </el-col>
            <el-col class="panel-col" :span="8">
                <div class="grid-content">
                    <div class="panel-header">
                        <div class="header-title"><i class="fa-solid fa-link"></i> 最新运行统计</div>
                    </div>
                    <div class="panel-body acp-height-auto">

                        <div class="count-box">
                            <ul>
                                <li class="count-box-item" style="width:calc(50% - 15px)" v-for="item in 4" :key="item">
                                    <div class="count-box-item-title">运行中</div>
                                    <div class="count-box-item-number">280</div>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </el-col>


        </el-row>

    </div>
</template>

<style lang="scss" scoped>
.count-box ul li.count-box-item{
    padding: 10px;
}
</style>