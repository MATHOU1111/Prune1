import { useState } from "react";
import {
    Button,
    Modal,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Input,
    Spinner,
    addToast
} from "@heroui/react";
import { useNavigate } from "react-router-dom";
import { Mail, KeyIcon } from "lucide-react";
import userService from "../../services/userService.js";

export default function ForgotPasswordModal({
    isOpen,
    onOpenChange
}) {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleSubmit = async () => {
        // Validation de base
        if (!email.trim()) {
            setError("Veuillez entrer votre adresse email");
            return;
        }

        // Validation format email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            setError("Veuillez entrer une adresse email valide");
            return;
        }

        try {
            setLoading(true);
            setError("");

            const result = await userService.requestPasswordReset(email);

            if (result.success) {
                addToast({
                    title: "Email envoyé",
                    description: "Un email de réinitialisation a été envoyé à votre adresse",
                    type: "success"
                });

                onOpenChange(false);
                // Optionnel : rediriger vers la page de reset
                navigate('/reset-password');
            } else {
                setError(result.error || "Erreur lors de l'envoi de l'email");
            }
        } catch (error) {
            setError("Erreur lors de l'envoi de l'email de réinitialisation");
        } finally {
            setLoading(false);
        }
    };

    const handleClose = () => {
        setEmail("");
        setError("");
        onOpenChange(false);
    };

    const handleEmailChange = (value) => {
        setEmail(value);
        if (error) setError(""); // Effacer l'erreur quand l'utilisateur tape
    };

    return (
        <Modal
            isOpen={isOpen}
            onOpenChange={onOpenChange}
            placement="top-center"
            classNames={{
                base: "bg-zinc-900 border border-zinc-700",
                header: "border-b border-zinc-700",
                body: "py-6",
                footer: "border-t border-zinc-700"
            }}
        >
            <ModalContent>
                {(onClose) => (
                    <>
                        <ModalHeader className="flex flex-col gap-1 text-white">
                            <div className="flex items-center gap-2">
                                <KeyIcon className="w-5 h-5" />
                                Mot de passe oublié
                            </div>
                        </ModalHeader>

                        <ModalBody>
                            <div className="space-y-4">
                                <div className="text-center text-zinc-300">
                                    <p className="mb-4">
                                        Entrez votre adresse email et nous vous enverrons un lien pour réinitialiser votre mot de passe.
                                    </p>
                                </div>

                                {/* Champ email */}
                                <Input
                                    type="email"
                                    label="Adresse email"
                                    placeholder="Entrez votre adresse email"
                                    value={email}
                                    onValueChange={handleEmailChange}
                                    startContent={<Mail className="text-zinc-400" size={18} />}
                                    variant="bordered"
                                    classNames={{
                                        input: "bg-transparent text-white",
                                        inputWrapper: "bg-zinc-800/50 border-zinc-700 hover:border-zinc-600 group-data-[focus=true]:border-primary"
                                    }}
                                    isRequired
                                    isDisabled={loading}
                                />

                                {/* Message d'erreur */}
                                {error && (
                                    <div className="text-red-400 text-sm text-center bg-red-400/10 border border-red-400/20 rounded-lg p-3">
                                        {error}
                                    </div>
                                )}
                            </div>
                        </ModalBody>

                        <ModalFooter>
                            <Button
                                color="default"
                                variant="light"
                                onPress={handleClose}
                                isDisabled={loading}
                                className="text-zinc-400"
                            >
                                Annuler
                            </Button>
                            <Button
                                color="primary"
                                onPress={handleSubmit}
                                isLoading={loading}
                                isDisabled={loading || !email.trim()}
                            >
                                {loading ? <Spinner size="sm" /> : 'Envoyer le lien'}
                            </Button>
                        </ModalFooter>
                    </>
                )}
            </ModalContent>
        </Modal>
    );
}