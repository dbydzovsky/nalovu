import React from 'react'
import { Image } from './Images'


export interface ImageProps {
    image: Image
}

export function MyImage(props: ImageProps) {
    const image = props.image;
    return <img src={image.src} width={image.width} height={image.height}/>
}