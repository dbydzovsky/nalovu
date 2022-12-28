import { UserRole } from "../../data/User";
import { GenericGameProps } from "./Game";
import { SelectMoneyFight } from "./SelectMoneyFight";
import { Waiting } from "./Waiting";



export function chooseScreen(props: GenericGameProps): React.ElementType<GenericGameProps> {
    const { game, user } = props
    const role = user.role
    const isAdmin = role === UserRole.Admin

    if (isAdmin) {
        return SelectMoneyFight
    }
    return Waiting
}