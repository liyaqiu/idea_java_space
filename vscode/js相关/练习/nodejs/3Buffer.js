

const bf = Buffer.from('eric') //将一个字符串转换为buffer
bf.toString() //将buffer转换为字符串
Buffer.alloc(10) //创建一个指定大小的buffer，并清空数据
Buffer.allocUnsafe(10)//创建一个指定大小的buffer，不情况数据