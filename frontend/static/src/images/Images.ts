import Background from './background.png'

export interface Image {
    src: string
    width: number
    height: number
}

type ImageMap = {
    [key: string]: Image;
};

export const Images: ImageMap = {
    xx: {
        src: Background,
        height: 372,
        width: 663
    }
}