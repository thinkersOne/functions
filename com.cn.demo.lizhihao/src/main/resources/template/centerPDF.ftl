<!DOCTYPE html>
<html>
<head lang="java">
    <meta charset="UTF-8"></meta>
    <title></title>
    <style>
        @page {
            size: 400mm 200mm;
            /*font-family: SimSun;*/
            /*font-size: 30px;*/
            @bottom-center{
                font-family: SimSun;
                font-size: 150px;
            };
        }
        .mt10{margin-top:10px;}
        .mt20{
            margin-top:20px;
            text-align: center;
            margin-left: 30px;
            width: 800px
        }
        .there{
            width: 100px;
            display: inline-block;
            margin-bottom: 10px;
        }
        table {
            background-color: transparent;
        }
        table,thead,tr,th,td{
            border: 1px solid #dfe6ec;
            border-collapse:collapse;
            padding: 5px;
        }
        h4{
            text-align: center;
        }
        th {
            text-align: left;
        }
        .dis{
            display: inline-block;
            width: 350px;
            padding: 10px 0px;
        }
        body{
            margin-left: 80px;
            margin-right: 80px;
        }
        .suojin{
            text-indent : 30px;
        }
        .jianyu{
            line-height:40px
        }
        .divheight{
            margin-bottom: 20px;
        }
    </style>
</head>
<body  style="font-family: SimSun">
    <h4><strong>票易通发票管理及供应链协同平台使用服务合同</strong></h4><br/><br/>
    <div class="divheight">
        <span class="there"><strong>甲方:</strong></span>
        <span>${firstCompanyName} </span><br/>
        <span class="there" >纳税识别号:</span>
        <span>${firstTaxNo}</span><br/>
        <span class="there" >地址:</span>
        <span>${firstCompanyAddr} </span><br/>
        <span class="there" >联系人:</span>
        <span>${firstUserName} </span><br/>
        <span class="there" >电话:</span>
        <span>${firstTel} </span><br/>
    </div>
    <div class="divheight">
        <span class="there"><strong>乙方:</strong></span>
        <span>上海云砺信息科技有限公司</span><br/>
        <span class="there">地址:</span>
        <span>上海市宝山区沪太路2999弄15号3层</span><br/>
        <span class="there">联系人:</span>
        <span>${secondUserName} </span><br/>
        <span class="there">电话:</span>
        <span>${secondTel} </span><br/>
    </div>
    <div class="divheight">
        <span class="there"><strong>丙方:</strong></span>
        <span>${threeCompanyName} </span><br/>
        <span class="there">地址:</span>
        <span>${threeCompanyAddr} </span><br/>
        <span class="there">联系人:</span>
        <span>${threeUserName} </span><br/>
        <span class="there">电话:</span>
        <span>${threeTel} </span><br/>
    </div>
    <div>鉴于：</div>
    <div  class="jianyu">
        <div>
            <div class="suojin">乙方作为丙方的技术支持合作商，设计开发“票易通发票管理及供应链协同平台”（以下简称平台），并为甲方提供平台的运营及技术支持服务。甲方希望通过使用平台，降低税务风险和营运成本、提升运作效率并节约人力及时间。</div>
            <div class="suojin">甲方为丙方的供应商，甲方按照丙方的规定或根据与丙方的协商为丙方提供货物或服务，并定期与丙方进行结算。</div>
            <div class="suojin">根据《中华人民共和国合同法》及其他相关法律法规的规定，经甲、乙、丙双方友好协商，就甲方使用平台及获得相关技术支持服务并向乙方支付相应服务费事宜，一致同意达成如下协议：</div>
        </div>
        <div>
            <div>一、服务内容</div>
            <div class="suojin">甲方通过平台帐号 购买乙方如下服务：</div>
            <table class="mt20">
                <thead>
                <tr>
                    <th class="mt20">订单编号</th>
                    <th class="mt20">服务名称</th>
                    <th class="mt20">服务内容</th>
                    <th class="mt20">服务期限<br/>(试用期除外)</th>
                    <th class="mt20">服务费用<br/>（含税，${rate}税率）</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td rowspan="${maxLength}">${orderNumber}</td>
                <#--已经选了服务情况-->
                   <#list pdfProductDTOList as pdfProductDTO>
                       <#if (pdfProductDTO_index > 0)>
                           <tr>
                               <td rowspan="${pdfProductDTO.productContentLength}">${pdfProductDTO.productName}</td>
                           <#list pdfProductDTO.productContents as productContent>
                               <#if (productContent_index = 0)>
                                   <td>${productContent}</td>
                                   <td rowspan="${pdfProductDTO.productContentLength}">${pdfProductDTO.totalAmount}</td>
                                   </tr>
                               <#elseIf (productContent_index > 0)>
                                   <tr><td>${productContent}</td></tr>
                               </#if>
                           </#list>
                       <#elseIf (pdfProductDTO_index =0)>
                         <td rowspan="${pdfProductDTO.productContentLength}">${pdfProductDTO.productName}</td>
                           <#list pdfProductDTO.productContents as productContent>
                               <#if (productContent_index = 0)>
                             <td>${productContent}</td>
                             <td rowspan="${maxLength}">${serviceExpireDate}</td>
                             <td rowspan="${pdfProductDTO.productContentLength}">${pdfProductDTO.totalAmount}</td>
                             </tr>
                               <#elseIf (productContent_index > 0)>
                             <tr><td>${productContent}</td></tr>
                               </#if>
                           </#list>
                       </#if>
                   </#list>
                <tr>
                    <td colspan="5">服务费用总计（含税，税率${rate}）：${totalAmount}元</td>
                </tr>
                </tbody>
            </table>
            <div  class='mt10'>二、服务费用支付方式</div>
            <div class='suojin'>1. 双方同意，乙方就甲方使用平台向甲方收取服务费。服务费按年收取，于服务开通当日收取该一年度的服务费。如甲方首次注册使用平台，则应在帐号开通后按一年度（开通的次日起）收取服务费。</div>
            <div class='suojin'>2. 双方同意，甲方应在收到乙方发出的缴费/续费邮件后十个工作日内（如甲方首次注册使用平台，则应在帐号开通后按一年度（开通的次日起）收取服务费）按平台付款指南将服务费汇入乙方指定银行帐号，乙方收到甲方服务费后五个工作日内在平台中更新甲方使用期限并开通相关功能。如甲方需要变更或中止相关服务，应提前30天通知乙方和丙方，如甲方在未通知乙方和丙方的情况下逾期付款15天以上，乙方和丙方有权中止甲方相关平台服务，由此造成的各方损失由甲方承担。</div>
            <div class='suojin'>3. 付款完成后，甲方可在平台中提交开票信息及相关文件，当乙方确认相关开票信息的真实性及有效性后，乙方应向甲方开具合法有效的发票。乙方将平台上根据订单自动生成的合同下载并打印两份，盖章后和开具的发票一起寄给甲方，甲方收到后盖章，一份自行存档，另一份寄回乙方存档。</div>
            <div class='mt10'>三、知识产权</div>
            <div class='suojin'>1. 乙方运营的票易通发票智能识别及验真服务软件著作权、所有权均归乙方所有，其所包含的知识产权亦归其所有。</div>
            <div class='suojin'>2. 在本协议有效期内，为履行本协议之目的，甲方仅在付款完成后拥有使用权。甲方不得将上述软件、信息、服务等用作本合同约定之外的其他任何用途。</div>
            <div class='suojin'>3. 甲方不得将乙方的软件产品解密破解、反编译或者交给第三方解密破解、反编译，否则乙方保留诉诸法律并向甲方追讨损失并索取侵权赔偿的权利。</div>
            <div class='mt10'>四、协议期限</div>
            <div class='suojin'>本服务协议与最早生效的订单同时生效，除非本协议另有约定，于最后到期服务的服务期限届满之日终止。</div>
            <div class='mt10'>五、本服务协议的附件为《用户服务条款》，附件《用户服务条款》与本服务协议主文共同构成完整协议；若《用户服务条款》与服务协议主文二者存不一致之处，以服务协议主文约定为准。</div>
            <div class='mt10'>六、本协议基于甲方在平台选择的服务内容和服务期限自动生成。甲方根据所选择的服务内容与服务期限支付了费用，即视为其已认可此服务协议的所有条款并受此约束。纸质协议的签署与否并不影响此协议的效力。</div>
        </div>
    </div>
    <br/><br/>
    <div>
        <span class="there"><strong>甲方:</strong></span>
        <span>${firstCompanyName} </span>
    </div>
    <div>
        <span class="there">日期:</span>
        <span>${currentTime}</span>
    </div>
    <div>
        <span class="there"><strong>乙方:</strong></span>
        <span>上海云砺信息科技有限公司</span>
    </div>
    <div>
        <span class="there">日期:</span>
        <span>${currentTime}</span>
    </div>
    <div>
        <span class="there"><strong>丙方:</strong></span>
        <span>${threeCompanyName}</span>
    </div>
    <div>
        <span class="there">日期:</span>
        <span>${currentTime}</span>
    </div>
</body>
</html>
